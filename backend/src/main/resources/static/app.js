document.addEventListener('DOMContentLoaded', () => {
    const generateBtn = document.getElementById('generate-btn');
    const requirementsInput = document.getElementById('requirements');
    const resultsSection = document.getElementById('results-section');
    const testCasesList = document.getElementById('test-cases-list');
    const loader = document.querySelector('.loader');
    const btnText = document.querySelector('.btn-text');
    const suiteTitle = document.getElementById('suite-title');
    const copyBtn = document.getElementById('copy-btn');

    let currentTestSuite = null;

    generateBtn.addEventListener('click', async () => {
        const requirement = requirementsInput.value.trim();

        if (!requirement) {
            alert("Please enter some requirements first.");
            return;
        }

        // UI Loading State
        setLoading(true);
        resultsSection.classList.add('hidden');
        testCasesList.innerHTML = '';

        try {
            const response = await fetch('/api/generate', {
                method: 'POST',
                headers: {
                    'Content-Type': 'text/plain'
                },
                body: requirement
            });

            if (!response.ok) {
                throw new Error(`Server error: ${response.status}`);
            }

            const testSuite = await response.json();
            currentTestSuite = testSuite;

            renderTestCases(testSuite);
            resultsSection.classList.remove('hidden');

            // Scroll to results
            resultsSection.scrollIntoView({ behavior: 'smooth' });

        } catch (error) {
            console.error('Error generating test cases:', error);
            alert(`Failed to generate test cases: ${error.message}. Ensure backend is running.`);
        } finally {
            setLoading(false);
        }
    });

    copyBtn.addEventListener('click', () => {
        if (!currentTestSuite) return;
        navigator.clipboard.writeText(JSON.stringify(currentTestSuite, null, 2))
            .then(() => {
                const originalText = copyBtn.innerText;
                copyBtn.innerText = 'Copied!';
                setTimeout(() => copyBtn.innerText = originalText, 2000);
            })
            .catch(err => console.error('Failed to copy json', err));
    });

    function setLoading(isLoading) {
        generateBtn.disabled = isLoading;
        if (isLoading) {
            loader.classList.remove('hidden');
            btnText.textContent = 'Generating...';
        } else {
            loader.classList.add('hidden');
            btnText.textContent = 'Generate Test Cases';
        }
    }

    function renderTestCases(suite) {
        if (suite.test_suite_name) {
            suiteTitle.textContent = suite.test_suite_name;
        }

        if (!suite.cases || suite.cases.length === 0) {
            testCasesList.innerHTML = '<p class="text-secondary">No test cases generated.</p>';
            return;
        }

        suite.cases.forEach(testCase => {
            const card = document.createElement('div');
            card.className = 'test-case-card';

            const stepsHtml = testCase.steps.map(step => `<li>${step}</li>`).join('');

            card.innerHTML = `
                <div class="tc-header">
                    <span class="tc-id">${testCase.id}</span>
                </div>
                <div class="tc-desc">${testCase.description}</div>
                
                <div class="tc-steps-title">Steps</div>
                <ul class="tc-steps">
                    ${stepsHtml}
                </ul>

                <div class="tc-expected-title">Expected Result</div>
                <div class="tc-expected">${testCase.expected ? testCase.expected : 'N/A'}</div>
            `;

            testCasesList.appendChild(card);
        });
    }
});
